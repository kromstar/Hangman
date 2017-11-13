import codecs

id_student = "Farte Catalin"

inputFile = codecs.open("cuvinte_de_verificat", "r", "utf-8")
outputFile = codecs.open("date_iesire_timestamp", "w", "utf-8")

litere = "IERALTONCUMĂSPDȚGBZFȘVHÂÎJXKYWQ"


def read_games(): # THE NAME OF THE METHOD IS OBVIOUSLY
    games = []
    for lines in inputFile.readlines():  
        list = []
        for game in lines.split(";"):  
            list.append(game.replace("\r\n", ""))  # APPEND TO THE LIST WITHOUT NEWLINE. ex: [[1001],[S**D**T],[STUDENT]]
        games.append(list)  # GAME INSTANCES
    return games


def unde_se_potriveste_litera(id_student, idJoc, wordToCheck, char):
    positions = []
    for i in range(0, len(wordToCheck)):
        if char == wordToCheck[i]:  # IF CHAR EQUALS THE CHAR ON POSITION i FROM THE COMPLETE WORD
            positions.append(i)  # THEN THE POSITION IS REMEMBERED
    return positions


def verificaCuvantul(id_student, idJoc, wordToGuess, wordToCheck):
    return wordToGuess == wordToCheck  # RETURN 1 IF TRUE AND 0 IF FALSE


def main():
    games = read_games()
    totalTries = 0
    totalGames = 0
    results = []
    for game in games:
        totalGames += 1
        tries = 0
        idJoc = game[0] # 
        wordToGuess = []
        wordToCheck = []
        for caracter in game[1]:  
            wordToGuess.append(caracter)  # THE WORD WITH * IS ADDED TO LIST wordToGuess
        for caracter in game[2]:  
            wordToCheck.append(caracter)  # --||-- FOR THE COMPLETE WORD
        while not verificaCuvantul(id_student, idJoc, wordToGuess, wordToCheck):  # WHILE wordToGuess STILL HAS *
            for char in litere:
                if verificaCuvantul(id_student, idJoc, wordToGuess, wordToCheck):  # EXCEPTION MADE TO REDUCE THE NUMBER OF TRIES
                    break  # THE PROGRAM GOES FOR ONE MORE TRY AFTER GUESSING THE WORD. USING THIS 'if' STOPS THE PROGRAM DOING THAT
                tries += 1
                positions = unde_se_potriveste_litera(id_student, idJoc, wordToCheck, char)
                for pozitie in positions:  # FOR EVERY POSITION THAT CONTAINS *
                    wordToGuess[pozitie] = char  # * IS REPLACED WITH THE CURRENT CHAR(LETTER)
                    ## For testing print(wordToGuess)
        results2 = [idJoc, tries]  # RESULTS2 IS A LIST CONTAINING THE GAME ID AND THE NUMBER OF TRIES FOR EACH GAME(USED TO MAKE THE PRINTS IN THE END)
        results.append(results2)  # RESULTS IS A LIST CONTAINING THE ITEMS OF RESULTS 2. EX: [1001,23],[1002,24]...
        totalTries += tries
        outputFile.write(idJoc + ";" + str(tries) + " incercari\n")
    print("Numarul total de jocuri este ", totalGames)
    print("Numarul total de tries este ", totalTries)
    for rezultat in results:
        print(rezultat[0] + ';' + str(rezultat[1]) + " incercari")


if __name__ == '__main__':
    main()
