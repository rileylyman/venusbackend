package venusbackend.riscv.insts.dsl.impls.extensions.b32

import venusbackend.riscv.InstructionField
import venusbackend.riscv.MachineCode
import venusbackend.riscv.insts.dsl.impls.InstructionImplementation
import venusbackend.riscv.insts.dsl.impls.signExtend
import venusbackend.riscv.insts.floating.Decimal
import venusbackend.simulator.Simulator

/**
 * Created by thaum on 8/6/2018.
 */
class FITypeImplementation32(private val eval: (Int, Simulator) -> Decimal) : InstructionImplementation {
    override operator fun invoke(mcode: MachineCode, sim: Simulator) {
        val rs1 = mcode[InstructionField.RS1].toInt()
        val rd = mcode[InstructionField.RD].toInt()
        val vrs1 = sim.getReg(rs1).toInt()
        val imm = signExtend(mcode[InstructionField.IMM_11_0].toInt(), 12)
        sim.setFReg(rd, eval(vrs1 + imm, sim))
        sim.incrementPC(mcode.length)
    }
}