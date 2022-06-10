import ast
from black import out
import requests
moves = [""]
for x in range(1,827):
    res = requests.get("https://pokeapi.co/api/v2/move/" + str(x))
    data = ast.literal_eval(str(res.json()))
    type = ast.literal_eval(str(data["type"]))
    moves.append(str(data["id"]) + ";" + data["name"] + ";" + str(data["accuracy"]) + ";" + str(data["power"]) + ";" + type["name"] + "\n")
    print(str(data["id"]))
with open("moves.txt","w") as output:
    output.writelines(moves)
