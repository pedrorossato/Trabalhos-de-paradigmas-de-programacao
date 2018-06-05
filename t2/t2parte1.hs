--Exec 1
geraTabela :: Int -> [(Int, Int)]
geraTabela n = if n > 0
	then (n, n^2) : geraTabela (n-1)
	else []
--Exec 2
contido :: Char -> String -> Bool
contido c "" = False
contido c p = if (c == (head p)) then True else contido c (tail p)
--Exec 3
translate :: [(Float,Float)] -> [(Float,Float)]
translate [] = []
translate lista = (fst (head lista)+2, snd (head lista)+2) : translate (tail lista)
--Exec 4
auxiliar :: Int -> Int -> [(Int,Int)]
auxiliar x y = if x == y+1 then [] else (x, x^2) : auxiliar (x+1) y
retorno :: Int -> [(Int,Int)]
retorno y = auxiliar 1 y