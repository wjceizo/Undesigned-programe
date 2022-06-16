from flask import Flask,request,jsonify
from person_dection import ssd_fimage_demo
import base64
import json
import numpy as np
import cv2 as cv
import os, traceback, sys 
from datetime import datetime
from flask_cors import CORS, cross_origin


app = Flask(__name__)
CORS(app, supports_credentials=True, resources=r'/*')
 
def tocv2(besedata):
    im_bytes = base64.b64decode(besedata)
    im_arr = np.frombuffer(im_bytes, dtype=np.uint8)  # im_arr is one-dim Numpy array
    img = cv.imdecode(im_arr, flags=cv.IMREAD_COLOR)
    return img


@app.route('/posts',methods=['post','get'])
@cross_origin()#通过装饰路线具体CORS 
def index():
    data = request.get_data(as_text=True)  # 获取值
    print(data)
    data=json.loads(data)
    # print(data)
    alldata = data['data'].split(',')
    besedata = alldata[1]
    img = tocv2(besedata)
    rebase,peoplenumbers = ssd_fimage_demo(img)
    response={
        "students":peoplenumbers,
        "img":rebase
    }
    
    return jsonify(response)

@app.route('/tryposts/',methods=['post','get','OPTIONS'])
def trydata():
    data = request.get_data(as_text=True)  # 获取值
    print(data)
    return data

@app.route('/test',methods=["POST"])
@cross_origin()#通过装饰路线具体CORS 
def test():
    if request.method == "POST":
        data = request.get_data(as_text=True)  # 获取值
        print(data)
    return data