// formation 'F1' asset 'SHIP_CARGO' bazier() start(1, 2) flyto(10, 11, 12, 13, 14, 15) delay 100.ms flyto(21, 22, 23, 24, 25, 26)
def f1 = formation 'F1' asset 'SHIP_CARGO' bazier 1, 2 flyto 10, 11, 12, 13, 14, 15 delay 100.ms flyto 21, 22, 23, 24, 25, 26
println("A:' ${f1.getClass()} -> ${f1}'")
