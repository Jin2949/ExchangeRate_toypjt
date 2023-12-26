#사용 예
import pymysql
def find_user():
    conn = pymysql.connect(host='localhost', user='root', password='0000', db='rate', charset='utf8')
    cur = conn.cursor()
    sql = 'select * from rate'
    cur.execute(sql)
    conn.commit()
    conn.close()
    row = cur.fetchall()
    return row