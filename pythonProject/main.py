from fastapi import FastAPI
import find_rate
import db_connect
from send_email import send_email

# 실행 명령어
# uvicorn main:app --reload
app = FastAPI()

@app.get("/")
def read_root():
    return {"message": "Hello, FastAPI!"}

@app.get("/findPrice")
def findPrice():
    price = find_rate.get_exchange_rate().replace(',', '')
    return {"price": price}

@app.get("/findUser")
def findUser():
    row = db_connect.find_user()
    now_price = float(find_rate.get_exchange_rate().replace(',', ''))

    for r in row:
        # r : id, email, minus, plus, price
        print(r)
        # 차이가 너무 크면 안보냄.(200원차이 이내만 가능)
        if abs(now_price - r[4]) < 200:
            # 현재가격이 상한가보다 비싸면 알람
            if now_price >= r[3]:
                print("올림보냄")
                send_email(r[1], "상한가보다 올라갔습니다.")
            # 현재가격이 하한가보다 싸면 알람
            elif now_price <= r[2]:
                print("내림보냄")
                send_email(r[1], "하한가보다 내려갔습니다.")

    return {"message": "findUser"}