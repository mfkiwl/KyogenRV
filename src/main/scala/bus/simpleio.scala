// See README.md for license details.
package bus

import chisel3.{Bool, _}

// bus ctrl
class CtrlSwChannel extends Bundle {
    val halt: Bool = Input(Bool())         // CPU or TraceMaster halt

    val r_add: UInt = Output(UInt(32.W))    // for debug: address dump
    val r_dat: UInt = Output(UInt(32.W))    // for test: memory dump

    val w_add: UInt = Input(UInt(32.W))     // for test: write address
    val w_dat: UInt = Input(UInt(32.W))     // for test: write data

    val g_add: UInt = Input(UInt(32.W))     // General register address (0 to 31)
    val g_dat: UInt = Output(UInt(32.W))    // General register data

    val r_pc: UInt = Output(UInt(32.W))    // Program Counter Read register
    val w_pc: UInt = Input(UInt(32.W))     // Program Counter Write register
//    val misc: Bool = Output(Bool())          // misc for test

    // EX stage iotester
    val r_ex_raddr1:UInt = Output(UInt(32.W))
    val r_ex_raddr2:UInt = Output(UInt(32.W))
    val r_ex_rs1:UInt = Output(UInt(32.W))
    val r_ex_rs2:UInt = Output(UInt(32.W))
    val r_ex_imm:UInt = Output(UInt(32.W))

    // MEM Stage
    val r_mem_alu_out:UInt = Output(UInt(32.W))
    // r_mem_alu_out_cmp

    // WB stage
    val r_wb_alu_out:UInt = Output(UInt(32.W))
    val r_wb_rf_wdata:UInt = Output(UInt(32.W))
    val r_wb_rf_waddr:UInt = Output(UInt(32.W))

    // STALL Signal
    val r_stall_sig:UInt = Output(UInt(32.W))

    // External interrupt signal
    val w_interrupt_sig: Bool = Input(Bool())

    // waitrequest signal
    val w_waitrequest_sig: Bool = Input(Bool())
    val w_datawaitreq_sig: Bool = Input(Bool())

    // waitrequest data signal
    //val w_waitreqdata_sig: Bool = Input(Bool())

}

// address channel bundle
class AddressChannel extends Bundle {
    val addr: UInt = Output(UInt(32.W))         // address (32bit)
}

// data channel bundle
class DataChannel extends Bundle {
    val req: Bool = Input(Bool())               // request signal
    val ack: Bool = Output(Bool())              // data is available ack
    val data: UInt = Output(UInt(32.W))         // data (32bit)
}

class wDataChannel extends Bundle {
    val req: Bool = Output(Bool())              // request signal
    val ack: Bool = Input(Bool())               // data is available ack
    val data: UInt = Output(UInt(32.W))         // data (32bit)
    val byteenable: UInt = Output(UInt(4.W))    // byteenable (8bit lane x4 = 32bit)
}

// HOST :read only(IMem)
// HOST :read/Write(Dmem)
class HostIf extends Bundle {
    // Instruction Memory
    // IO definition
    val imem_add: AddressChannel    = new AddressChannel
    val r_imem_dat: DataChannel     = Flipped(new DataChannel)  // read operation
    val w_imem_dat: wDataChannel    = new wDataChannel          // write operation

    // data Memory
    val dmem_add: AddressChannel    = new AddressChannel
    val r_dmem_dat: DataChannel     = Flipped(new DataChannel)  // read operation
    val w_dmem_dat: wDataChannel    = new wDataChannel          // write operation

    // debug if
    val sw: CtrlSwChannel           = new CtrlSwChannel
}

// Memory-Mapped Slave IF
// Slave :read/Write(IMem)
// Slave :read/Write(Dmem)
class SlaveIf_Inst extends Bundle {
    // IO definition
    val imem_add: AddressChannel    = Flipped(new AddressChannel)
    val r_imem_dat: DataChannel     = new DataChannel               // read operation
    val w_imem_dat: wDataChannel    = Flipped(new wDataChannel)     // write operation
}

class SlaveIf_Data extends Bundle {
    // data Memory
    // IO definition
    val dmem_add: AddressChannel    = Flipped(new AddressChannel)
    val r_dmem_dat: DataChannel     = new DataChannel               // read operation
    val w_dmem_dat: wDataChannel    = Flipped(new wDataChannel)     // write operation
}

class TestIf extends Bundle {
    val sw: CtrlSwChannel           = new CtrlSwChannel
}