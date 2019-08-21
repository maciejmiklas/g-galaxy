def e1 = element 'EC1' asset 'SHIP_CARGO' bazier 1, 2 flyto 10, 11, 12, 13, 14, 15 delay 100.ms flyto 21, 22, 23, 24, 25, 26 bazier 31, 32 flyto 310, 311, 312, 313, 314, 15 flyto 321, 322, 323, 324, 325, 326
println("A:' ${e1.getClass()} -> ${e1}'")

def e2 = element 'EI1' asset 'SHIP_INTERCEPTOR_RED' bazier 11, 12 flyto 110, 111, 112, 113, 114, 115 delay 100.ms flyto 121, 122, 123, 124, 125, 126
println("B:' ${e2.getClass()} -> ${e2}'")

formation 'EI1', 'EC1'