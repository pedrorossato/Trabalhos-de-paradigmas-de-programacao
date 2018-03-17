
isVowel :: Char -> Bool
isVowel x = x ==('a') || x==('e') || x==('i') || x==('o') || x==('u')

addComa :: String -> String
addComa x=x++","

funchtml:: String -> String
funchtml s = ("<LI>"++ s ++"</LI>")

htmlListItems :: [String] -> [String]
htmlListItems string = map (funchtml) string


