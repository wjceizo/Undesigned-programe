import base64

def tobase(img_path):
    img = open(img_path,'rb')
    res = img.read()
    s = base64.b64encode(res)
    img.close()
    # print(s.decode('ascii'))
    return s.decode('ascii')