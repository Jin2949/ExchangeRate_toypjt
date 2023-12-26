import requests
from bs4 import BeautifulSoup

def get_exchange_rate():
    url = 'https://www.investing.com/currencies/usd-krw'

    # Send a GET request to the URL
    response = requests.get(url)

    # Check if the request was successful (status code 200)
    if response.status_code == 200:
        # Parse the HTML content of the page
        soup = BeautifulSoup(response.text, 'html.parser')

        # Find and print the exchange rate (you may need to adjust the HTML structure)
        exchange_rate = soup.find('span', class_='text-2xl').text.strip()

        print(f'USD to KRW Exchange Rate: {exchange_rate}')
        return exchange_rate
    else:
        print(f"Failed to retrieve the page. Status code: {response.status_code}")
        return 9999999

# Get the exchange rate
# get_exchange_rate()