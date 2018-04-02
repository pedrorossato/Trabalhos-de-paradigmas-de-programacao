inteiro :: String -> [Int]
inteiro [] = []
inteiro (x:xs) = (read [x]:: Int) : (inteiro xs)
vezes3 [] = 0
vezes3 x = ((head x) * 3) + (vezes1(tail x))
vezes1 [] = 0
vezes1 x = ((head x) * 1) + (vezes3(tail x))
isEanOk :: String -> Bool
isEanOk x = if(length x /= 13)
	then False
	else ((last (inteiro x)) + (vezes1 (init (inteiro x)))) `mod` 10 == 0