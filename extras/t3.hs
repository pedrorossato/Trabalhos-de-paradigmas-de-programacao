import Data.Char

zeroInit:: String -> Bool
zeroInit (x:xs) = if (x == '0') then True else False

has5:: [Int] -> Bool
has5 x = if (length x) == 5 then True else False

hasN::[Int] -> Int -> Bool
hasN x y = if length x == y then True else False

potN0:: Int -> [Int]
potN0 n = [2^x| x<- [n,(n-1)..1]]

zipmult:: [Int] -> [Int] -> [Int]
zipmult [] [] = []
zipmult (x:xs) (y:ys) = (x*y):(zipmult xs ys)

potencias:: Int -> [Int]
potencias n = [2^n| n <- [0..(n-1)]]

positivos:: [Int] -> [Int]
positivos [] = []
positivos (x:xs) = if x > 0 then x:(positivos xs) else (positivos xs)

mesmaposicao :: Char -> [Char] -> [Char] -> Bool
mesmaposicao c (x:xs) (y:ys) = if ((c == x) && (c == y)) then True else (mesmaposicao c xs ys)

