package venusbackend.riscv.insts.dsl.types.floating

import venusbackend.riscv.insts.dsl.types.Instruction
import venusbackend.riscv.insts.dsl.disasms.extensions.FRRTypeDisassembler
import venusbackend.riscv.insts.dsl.formats.extensions.FSRS2TypeFormat
import venusbackend.riscv.insts.dsl.impls.NoImplementation
import venusbackend.riscv.insts.dsl.impls.extensions.b32.FFRRTypeImplementation32
import venusbackend.riscv.insts.dsl.parsers.extensions.FRRTypeParser
import venusbackend.riscv.insts.floating.Decimal

class FtRTypeInstruction(
    name: String,
    opcode: Int,
    funct3: Int,
    funct7: Int,
    rs2: Int,
    // eval16: (Short, Short) -> Short = { _, _ -> throw NotImplementedError("no rv16") },
    eval32: (Decimal, Decimal) -> Int // ,
    // eval64: (Long, Long) -> Long = { _, _ -> throw NotImplementedError("no rv64") },
    // eval128: (Long, Long) -> Long = { _, _ -> throw NotImplementedError("no rv128") }
) : Instruction(
    name = name,
    format = FSRS2TypeFormat(opcode, funct7, rs2),
    parser = FRRTypeParser,
    impl16 = NoImplementation,
    impl32 = FFRRTypeImplementation32(eval32),
    impl64 = NoImplementation,
    impl128 = NoImplementation,
    disasm = FRRTypeDisassembler
)