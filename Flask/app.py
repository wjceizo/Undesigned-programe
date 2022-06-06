from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import os

app = Flask(__name__)


app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:Wjc112299@127.0.0.1:3306/webtest'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

class Student(db.Model):  # 表名将会是 student
    Stid = db.Column(db.Integer, primary_key=True) # 主键
    Stu_id = db.Column(db.Integer)  # StudentId
    Stu_name = db.Column(db.String(40))  # StudentName
    Stu_class = db.Column(db.String(40)) # StudentClass
    Stu_password = db.Column(db.String(200)) # StudentPassword

class Teacher(db.Model):  # 表名将会是 teacher
    Tid = db.Column(db.Integer, primary_key=True)  # 主键
    Tea_id = db.Column(db.Integer)  # 主键
    Tea_name = db.Column(db.String(40))  # TeacherName
    Tea_class = db.Column(db.String(40))  # TeacherClass
    Tea_password = db.Column(db.String(200)) # TeacherPassword

class Signin(db.Model):  # 表名将会是 signin
    Sid = db.Column(db.Integer, primary_key=True)  # 主键
    sign_id = db.Column(db.DateTime)    # SignId
    sign_time = db.Column(db.DateTime)  # SignTime
    sign_status = db.Column(db.Integer) # SignStatus
    Tea_id = db.Column(db.Integer)  # TeacherId
    student_id = db.Column(db.Integer) # StudentId

class Interaction(db.Model):  # 表名将会是 interaction
    Iid = db.Column(db.Integer, primary_key=True)  # 主键
    Int_id = db.Column(db.Integer)  # InteractionId
    Int_time = db.Column(db.DateTime)   # InteractionTime
    Int_status = db.Column(db.Integer)  # InteractionStatus
    Tea_id = db.Column(db.String(200))  # TeacherId
    student_id = db.Column(db.String(200)) # StudentId

@app.route('/')
def index():
    return 'Welcome!'

@app.route('/index')
def index():
    return 'Welcome My Friend!'
    

if __name__ == '__main__':
    app.run()
