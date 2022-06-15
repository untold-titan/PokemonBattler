import ast
from ctypes import sizeof
from black import out
import requests

def calculateHP(base):
    HP = 2 * base + 204
    return str(HP)

def calculateStat(base):
    stat = (2 * base + 99) * 1.1
    return str(int(stat))

pkmns = [""]
for x in range(1,899):
    res = requests.get("https://pokeapi.co/api/v2/pokemon/" + str(x))
    data = ast.literal_eval(str(res.json()))
    pkmn = str(data["id"]) + ";" + str.capitalize(data["name"]) + ";"
    types = ast.literal_eval(str(data["types"]))
    type1 = ast.literal_eval(str(types[0]["type"]))
    pkmn += type1["name"] + ";"
    if(len(types) == 2):
        type2 = ast.literal_eval(str(types[1]["type"]))
        pkmn += type2["name"] + ";"
    else:
        pkmn += type1["name"] + ";"
    stats = ast.literal_eval(str(data["stats"]))
    pkmn += calculateHP(stats[0]["base_stat"])  + ";" # HP 
    pkmn += calculateStat(stats[1]["base_stat"])  + ";" # Attack
    pkmn += calculateStat(stats[2]["base_stat"])  + ";" # Defense
    pkmn += calculateStat(stats[3]["base_stat"])  + ";" # Sp. Attack
    pkmn += calculateStat(stats[4]["base_stat"])  + ";" # Sp. Defense
    pkmn += calculateStat(stats[5]["base_stat"])  + ";" # Speed
    print(str(data["id"]) + "   " + str.capitalize(data["name"]))
    pkmn += "\n"
    pkmns.append(pkmn)

with open("pokemon.txt","w") as output:
    output.writelines(pkmns)


