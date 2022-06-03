import bs4, urllib3
import sys

query = sys.argv[1]
output_file = sys.argv[2]

http = urllib3.PoolManager()
url = "https://en.wikipedia.org/wiki/" + query
response = http.request('GET', url)
soup = bs4.BeautifulSoup(response.data, features="html.parser")
div = soup.find("div", {"id": "content"})

text_file = open(output_file, "w")
text_file.write(str(div))
text_file.close()


