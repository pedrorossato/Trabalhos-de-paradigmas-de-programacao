--Exec 1
isBin :: String -> Bool
isBin "" = True
isBin p = if (head p =='0'|| head p =='1')
	then isBin (tail p)
	else False
--Exec 2
isBin2 :: String -> Bool
isBin2 p = if (length p == 0) 
	then False 
	else (notElem False (map(\x -> elem x "01") p))
--Exec 3
auxBin2Dec [] n = 0
auxBin2Dec (x:xs) n = (x*(2^n)) + auxBin2Dec xs ((length xs)-1)
bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)
--Exec 4
--Exec 5
aux 0 = [0]
aux 1 = [1]
aux x = (x `mod` 2) : (aux(x `div` 2))
dec2bin :: Int -> [Int] 
dec2bin x = reverse ( aux x )
--Exec 6
isHex :: String -> Bool
isHex "" = False
isHex str = notElem False (map (\n -> elem n "0123456789ABCDEF") str)