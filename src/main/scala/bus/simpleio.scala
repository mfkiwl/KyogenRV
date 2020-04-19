package bus

import chisel3._
import chisel3.util._
import chisel3.Bool

// bus ctrl
class CtrlSwChannel extends Bundle {
    val halt = Input(Bool())    // CPU or TraceMaster halt
    //val rw   = Input(Bool())   // indicate Memory-Mapped read(false)/Write(true) SW
    val addr = Output(UInt(32.W))   // for debug: address dump 
    val data = Output(UInt(32.W)) // for test: memory dump
    val wData = Input(UInt(32.W)) // for test: write data
    val wAddr = Input(UInt(32.W)) // for test: write address
}

// address channel bundle
class AddressChannel extends Bundle {
    val req     = Output(Bool())        // request signal
    val addr    = Output(UInt(32.W))   // address (32bit)
}

// data channel bundle
class DataChannel extends Bundle {
    val ack = Output(Bool())        // data is available ack
    val data = Output(UInt(32.W))   // data (32bit)
}

class wDataChannel extends Bundle {
    val ack = Input(Bool())        // data is available ack
    val data = Output(UInt(32.W))   // data (32bit)
}

// HOST :read only(IMem)
// HOST :read/Write(Dmem)
class HostIf extends Bundle {
    // IO definition
    val r_ach = new AddressChannel
    val r_dch = Flipped(new DataChannel)    // flipped I/O
    // write operation
    val w_ach = new AddressChannel   
    val w_dch = new wDataChannel	
    // debug if
    val sw  = new CtrlSwChannel
}

// Memory-Mapped Slave IF
// Slave :read/Write(IMem)
// Slave :read/Write(Dmem)
class SlaveIf extends Bundle {
    // IO definition
    // read operation
    val r_ach = Flipped(new AddressChannel) // flipped I/O
    val r_dch = new DataChannel	
    // write operation
    val w_ach = Flipped(new AddressChannel) // flipped I/O
    val w_dch = Flipped(new wDataChannel)	

}

class TestIf extends Bundle {
    val sw = new CtrlSwChannel
}