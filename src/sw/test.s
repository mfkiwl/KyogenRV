# sample assemble file

_start0:
    nop
    addi x31, x0, 0xAA
_label1:
    addi  x1,  x0, 1
    addi  x2,  x0, 2    # x1 = x0 + 1 = 1
    addi  x3,  x0, 3    # x2 = x0 + 1 = 2
    addi  x4,  x0, 4    # x2 = x0 + 1 = 2
    addi  x5,  x0, 5    # x2 = x0 + 1 = 2
    addi  x6,  x0, 6    # x2 = x0 + 1 = 2
    addi  x7,  x0, 7    # x2 = x0 + 1 = 2
    addi  x8,  x0, 8    # x2 = x0 + 1 = 2
    jal   x4,  _label1

    addi  x10,  x0, 9    # x2 = x0 + 1 = 2
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop

#      sw  x2,   8(x0)
#      nop
#      nop
#      nop
#      ld  x30,  8(x0)
#      nop
#      nop
#
#      addi  x3,  x0, 3    # x3 = x0 + 2 = 3
#      jal   x4,  _label4  # x4 => address(_label2), jump _label4
#
#  _label2:
#      addi  x6, x0, 6     # x6 = 6
#      addi  x7, x0, 7     # x7 = 7 (RETURN HERE)
#      addi  x8, x0, 8     # x8 = 8
#      addi  x9, x0, 9     # x9 = 9
#      addi x10, x0, 10    # x10= 0x0A
#      addi x11, x0, 11    # x11= 0x0B
#  _label3:
#      jalr  x0, x5,0      # jump to x5 (= _label5)
#
#  _label4:
#      addi x12,x0,12      # x12= 0x0C
#      jalr x5, x4, 4      # x5 => _label5, jump x4+4 (= _label2+4)
#
#  _label5:
#      jal  x0, _label5    # forever loop