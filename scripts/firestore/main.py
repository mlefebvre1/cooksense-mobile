import json
import argparse
import requests

PROJECT_ID = "cooksense-14276"


def get_base_url(use_emulator: bool) -> str:
    if use_emulator:
        return f"http://localhost:8080/v1/projects/{PROJECT_ID}/databases/(default)/documents"
    return f"https://firestore.googleapis.com/v1/projects/{PROJECT_ID}/databases/(default)/documents"


def get_headers(use_emulator: bool) -> dict:
    if use_emulator:
        return {}

    from google.oauth2 import service_account
    from google.auth.transport.requests import Request as GoogleRequest

    credentials = service_account.Credentials.from_service_account_file(
        "service-account.json", scopes=["https://www.googleapis.com/auth/datastore"]
    )
    credentials.refresh(GoogleRequest())

    if not credentials.token:
        raise RuntimeError("Failed to obtain access token from service account")

    return {"Authorization": f"Bearer {credentials.token}"}


def main():
    parser = argparse.ArgumentParser(description="Seed Firestore with recipes")
    parser.add_argument(
        "--cloud",
        action="store_true",
        help="Seed to Firestore cloud instead of local emulator",
    )
    args = parser.parse_args()

    use_emulator = not args.cloud
    base_url = get_base_url(use_emulator)

    print(f"Seeding to {'cloud' if args.cloud else 'emulator'}...")

    try:
        headers = get_headers(use_emulator)
    except Exception as e:
        print(f"Authentication failed: {e}")
        return

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

        response = requests.patch(
            f"{base_url}/recipes/{doc_id}", json={"fields": fields}, headers=headers
        )

        if response.status_code == 200:
            print(f"Added: {recipe['title']}")
        else:
            print(f"Failed: {recipe['title']} — {response.text}")

    print("Seeding complete!")


if __name__ == "__main__":
    main()
