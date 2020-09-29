#Utilizador Carolina
import requests


headers = {}
headers = ['Authorization'] = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTc3MTQwNDA3LCJqdGkiOiJlZDFmODE5YWJjYzA0Y2Y5OTJjMTUwMzUwODc3NTA1NCIsInVzZXJfaWQiOjE2fQ.zXz0utpvGJXMWcFWgeCmqCtnbAr1KsHGpZp-LgW3Y3U'

r = r.requests.get('http:localhost:8000/users', headers = headers)
print(r.text)