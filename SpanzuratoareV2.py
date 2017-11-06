import codecs

id_student = "Farte Catalin"

inputFile = codecs.open("cuvinte_de_verificat", "r", "utf-8")
outputFile = codecs.open("date_iesire_timestamp", "w", "utf-8")

litere = "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ"


def read_games():
    games = []
    for lines in inputFile.readlines():  # CITIRE LINIE CU LINIE DIN FISIERUL cuvinte_de_verificat
        list = []
        for game in lines.split(";"):  # SPLIT LA ;
            list.append(game.replace("\r\n", ""))  # APPEND LA LISTA FARA NEWLINE. ex: [[1001],[S**D**T],[STUDENT]]
        games.append(list)  # GAME INSTANCES
    return games


def unde_se_potriveste_litera(id_student, idJoc, wordToCheck, char):
    positions = []
    for i in range(0, len(wordToCheck)):
        if char == wordToCheck[i]:  # DACA LITERA ESTE LA FEL CU LITERA DE PE POZITIA I,
            positions.append(i)  # ATUNCI POZITIA SE RETINE
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
        idJoc = game[0]
        wordToGuess = []
        wordToCheck = []
        for caracter in game[1]:  # IN CONTINUARE CUVANTUL CU * V-A FI INTRODUS INTR-O LISTA,
            wordToGuess.append(caracter)  # CARACTER CU CARACTER
        for caracter in game[2]:  # LA FEL SI PENTRU,
            wordToCheck.append(caracter)  # CUVANTUL DE VERIFICARE
        while not verificaCuvantul(id_student, idJoc, wordToGuess, wordToCheck):  # CAT TIMP CUVANTUL MAI ARE *
            for char in litere:
                if verificaCuvantul(id_student, idJoc, wordToGuess, wordToCheck):  # O EXCEPTIE PENTRU A,
                    break  # REDUCE NUMARUL DE INCERCARI INUTILE
                tries += 1
                positions = unde_se_potriveste_litera(id_student, idJoc, wordToCheck, char)
                for pozitie in positions:  # PENTRU FIECARE POZITIE CE CONTINE *, * SE INLOCUIESTE,
                    wordToGuess[pozitie] = char  # CU LITERA CURENTA
                    ## For testing print(wordToGuess)
        results2 = [idJoc, tries]  # PENTRU AFISARE ULTERIOARA IN CAZ PARTICULAR LA FIECARE JOC
        results.append(results2)
        totalTries += tries
        outputFile.write(idJoc + ";" + str(tries) + " incercari\n")
    print("Numarul total de jocuri este ", totalGames)
    print("Numarul total de tries este ", totalTries)
    for rezultat in results:
        print(rezultat[0] + ';' + str(rezultat[1]) + " incercari")


if __name__ == '__main__':
    main()
