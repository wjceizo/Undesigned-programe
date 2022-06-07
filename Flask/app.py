from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import os
from base64 import urlsafe_b64decode 
from base64 import urlsafe_b64encode 
import requests
from flask import request
import cv2
from flask import jsonify
from werkzeug.utils import secure_filename
from tobase64 import tobase

app = Flask(__name__)


# app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:Wjc112299@127.0.0.1:3306/webtest'
# app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
# db = SQLAlchemy(app)

@app.route('/index')
def index():
    return 'Welcome My Friend!'


UPLOAD_PATH = os.path.join(os.path.dirname(__file__), 'images')
@app.route('/predict', methods=['GET', 'POST'])
def upload_pic():
    # 来获取多个上传文件
    imgs = request.files.getlist("file_imgs")
    urls = []
    # 上传文件夹如果不存在则创建
    if not os.path.exists(UPLOAD_PATH):
        os.mkdir(UPLOAD_PATH)

    filename = secure_filename(imgs[0].filename)
    print(filename)
    imgs[0].save(os.path.join(UPLOAD_PATH, filename))
    b = tobase('images/' + filename)
    print(b)
    


    respose = {
        "code": 200,
        "students": 5,
        "img": b
    }
    return jsonify(respose)



if __name__ == '__main__':
    app.run()
