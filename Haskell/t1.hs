import Data.Char
-------Exec 1----------
isVowel :: Char -> Bool
isVowel x = x ==('a') || x==('e') || x==('i') || x==('o') || x==('u')
------Exec 2---------
addComa :: String -> String
addComa x=x++","
------Exec 3 (sem anônima)-------
funchtml:: String -> String
funchtml s = ("<LI>"++ s ++"</LI>")

htmlListItems :: [String] -> [String]
htmlListItems string = map (funchtml) string
-----Exec 3 (com anônima)-------
palavra x = map(\x -> "<LI>" ++ x ++ "</LI>")x
-----Exec 4 (sem anônima)---------
naoVogal:: Char -> Bool
naoVogal l = isVowel l == False

semVogais:: String -> String
semVogais p= filter naoVogal p
-----Exec 4(com anônima)-------
semVogal p= filter(\x-> isVowel x ==False)p
-----Exec 5(sem anônima)------
codString x = if ( x /= ' ' ) then '-' else x
codifica p = map codString p
-----Exec 5(com anônima)------
alteracao p = map (\x -> if x == ' ' then ' ' else '-') p
-----Exec 6 ---------
firstName :: String -> String
firstName s= takeWhile(/=' ')s
-----Exec 7-------
isInt :: String -> Bool
isInt p = length ( filter ( not . \x -> elem x "0123456789") p ) == 0
-----Exec 8--------
lastName :: String -> String
lastName p= reverse(firstName(reverse p))
-----Exec 9------
userName:: String -> String
userName p= map toLower ([head(firstName p)] ++ (lastName p))
-----Exec 10------
trocaValor:: Char -> Char
trocaValor c
  | c == 'a' = '4'
  | c == 'e' = '3'
  | c == 'i' = '2'
  | c == 'o' = '1'
  | c == 'u' = '0'  
encodeName:: String -> String
encodeName p = map (\c -> if (isVowel c) then (trocaValor c) else c) p
-----Exec 11---------
-----Exec 12-------
truncadasounao :: [String] -> [String]
truncadasounao list = map (\str -> if (length str) > 10 then (take 10 str) else (take 10 (str ++ ".........."))) list