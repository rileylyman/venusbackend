package venusbackend.assembler

/**
 * Thrown when errors occur during assembly.
 *
 * @todo split this into AssemblerUserError and AssemblerError
 */
class AssemblerError : Throwable {
    var line: Int? = null
    var errorType: Throwable? = null

    /**
     * @param msg the message to error with
     */
    constructor(msg: String? = null) : super(msg)

    /**
     * @param errorLine the line the error occurred on
     * @param e the original error to pass along
     */
    constructor(errorLine: Int, e: Throwable) : this(e.message) {
        line = errorLine
    }

    constructor(msg: String? = null, errorType: Throwable) : super(msg) {
        this.errorType = errorType
    }

    override fun toString(): String {
        if (line == null) return super.toString()
        else return "${super.toString()} on line $line"
    }
}
