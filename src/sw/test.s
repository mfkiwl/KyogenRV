# riscv64-unknown-elf-as test.S -o test.elf
# riscv64-unknown-elf-objcopy --gap-fill 0 -O binary test.elf test.bin
# od -An -tx4 -w4 test.bin >> test.txt
#

_start:
    nop
    addi x1,x0,1;# (x1 = x0 + 1 = 1)
    addi x2,x0,1;# (x2 = x0 + 1 = 1)
    addi x3,x0,2;# (x3 = x0 + 2 = 2)
    addi x4,x0,3;# (x4 = x0 + 3 = 3)
    addi x5,x0,4
    addi x6,x0,5
_start2:
    addi x7,x0,6
    addi x8,x0,7
    addi x9,x0,8
    addi x10,x0,9
    addi x11,x0,10;# (x11 = 0 + 10 = 10)
    slli x1,x11,2;#  (x1 = x11 << 2 = 40)
    addi x12,x0,11;# (x12 = 11)
    add x3,x11,x12;# (x3 = x11 + x12 = 10 + 11 = 21 = 0x15)
    addi x13,x0,12
    addi x14,x0,13
    addi x15,x0,14
    addi x16,x0,15
    addi x17,x0,16
    addi x18,x0,17
    addi x19,x0,18
    addi x20,x0,19
    addi x21,x0,20
    addi x22,x0,21
    addi x23,x0,22
    addi x24,x0,23
    addi x25,x0,24
    addi x26,x0,25
    addi x27,x0,26
_start3:
    addi x28,x0,27
    addi x29,x0,28

    jal  x1, _start4 #(x1 = pc+4.U, pc = x1 + 0 = 40 = 0x28)
    addi x30,x0,29
    addi x31,x0,30
_start4:
    jal  x0,_start3
#    addi x1,x0,2 #(x1 = x0 + 2 = 2)
#    addi x2,x0,2 #(x2 = x0 + 3 = 3)
