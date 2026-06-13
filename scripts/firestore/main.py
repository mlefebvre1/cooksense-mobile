import json
import requests

PROJECT_ID = "cooksense-14276"
EMULATOR_HOST = "localhost:8080"
BASE_URL = (
    f"http://{EMULATOR_HOST}/v1/projects/{PROJECT_ID}/databases/(default)/documents"
)

with open("seed_recipes.json") as f:
    recipes = json.load(f)

for recipe in recipes:
    doc_id = recipe["id"]

    fields = {
        key: (
            {"stringValue": value}
            if isinstance(value, str)
            else {"arrayValue": {"values": [{"stringValue": v} for v in value]}}
        )
        for key, value in recipe.items()
    }

    response = requests.patch(f"{BASE_URL}/recipes/{doc_id}", json={"fields": fields})

    if response.status_code == 200:
        print(f"Added: {recipe['title']}")
    else:
        print(f"Failed: {recipe['title']} — {response.text}")

print("Seeding complete!")
