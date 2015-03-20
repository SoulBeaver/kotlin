package kotlin.io

import java.io.File
import java.util.NoSuchElementException

private fun String.getRootName(): String {
    // Note: separators should be already replaced to system ones
    var first = indexOf(File.separatorChar, 0)
    if (first == 0) {
        if (length() > 1 && this[1] == File.separatorChar) {
            // Network names like //my.host/home/something => //my.host/home/ should be root
            first = indexOf(File.separatorChar, 2)
            if (first >= 0) {
                first = indexOf(File.separatorChar, first + 1)
                if (first >= 0)
                    return substring(0, first + 1)
            }
        }
        return substring(0, 1)
    }
    // C:\
    if (first > 0 && this[first - 1] == ':') {
        first++
        return substring(0, first)
    }
    // C:
    if (first == -1 && endsWith(':'))
        return this
    return ""

}

/**
 * Returns a string representation of root component of this abstract name, like / from /home/user, or C:\ from C:\file.tmp,
 * or //my.host/home for //my.host/home/user,
 * or empty string if this name is relative, like bar/gav
 */
public val File.rootName: String
    get() = separatorsToSystem().getRootName()

/**
 * Returns root component of this abstract name, like / from /home/user, or C:\ from C:\file.tmp,
 * or //my.host/home for //my.host/home/user,
 * or null if this name is relative, like bar/gav
 */
public val File.root: File?
    get() {
        val name = rootName
        return if (name.length() > 0) File(name) else null
    }

public data class FilePathComponents(public val rootName: String, public val fileList: List<File>) {
    public val size: Int = fileList.size()

    public fun subPath(beginIndex: Int, endIndex: Int): File {
        if (beginIndex < 0 || beginIndex >= endIndex || endIndex > size)
            throw IllegalArgumentException()

        var res = ""
        var first = true
        for (elem in fileList.subList(beginIndex, endIndex)) {
            if (!first) {
                res += File.separatorChar
            } else {
                first = false
            }
            res += elem.toString()
        }
        return File(res)
    }
}

public fun File.filePathComponents(): FilePathComponents {
    val path = separatorsToSystem()
    val rootName = path.getRootName()
    val subPath = path.substring(rootName.length())
    // if: a special case when we have only root component
    // Split not only by / or \, but also by //, ///, \\, \\\, etc.
    val list = if (rootName.length() > 0 && subPath.isEmpty()) listOf() else
        subPath.split("""\Q${File.separatorChar}\E+""").toList().map{ it -> File(it) }
    return FilePathComponents(rootName, list)
}

/**
 * Returns a relative pathname which is a subsequence of this pathname,
 * beginning from component [beginIndex], inclusive,
 * ending at component [endIndex], exclusive.
 * Number 0 belongs to a component closest to the root,
 * number count-1 belongs to a component farthest from the root
 * @throws IllegalArgumentException if [beginIndex] is negative,
 * or [endIndex] is greater than existing number of components,
 * or [beginIndex] is greater than or equals to [endIndex]
 */
public fun File.subPath(beginIndex: Int, endIndex: Int): File = filePathComponents().subPath(beginIndex, endIndex)
