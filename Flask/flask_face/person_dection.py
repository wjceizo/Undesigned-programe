# from openvino.inference_engine import IECore
import numpy as np
import time
import cv2 as cv
from openvino.inference_engine import IECore
import base64



def cv2_base64(image):
    base64_str = cv.imencode('.jpg',image)[1].tobytes()
    base64_str = base64.b64encode(base64_str)
    return base64_str




def ssd_fimage_demo(needimg):
    ie = IECore()
    for device in ie.available_devices:
        print(device)

    # with open('object_detection_classes_coco.txt') as f:
    #     labels = [line.strip() for line in f.readlines()]

    model_xml = "face-detection-retail-0005.xml"
    model_bin = "face-detection-retail-0005.bin"
    net = ie.read_network(model=model_xml, weights=model_bin)
    input_blob = next(iter(net.input_info))
    out_blob = next(iter(net.outputs))

    n, c, h, w = net.input_info[input_blob].input_data.shape
    print(n, c, h, w)

    # src = cv.imread("people1.jpg")
    src = needimg
    image = cv.resize(src, (w, h))
    image = image.transpose(2, 0, 1)
    exec_net = ie.load_network(network=net, device_name="CPU")
    res = exec_net.infer(inputs={input_blob:[image]})

    ih, iw, ic = src.shape
    res = res[out_blob]
    print(res.shape)
    print(res[0][0][0])
    print(res[0][0][1])
    peoplenumbers = 0
    for obj in res[0][0]:
        if obj[2] > 0.4:
            peoplenumbers = peoplenumbers + 1
            print(obj[3])
            print(iw)
            xmin = int(obj[3] * iw)
            ymin = int(obj[4] * ih)
            xmax = int(obj[5] * iw)
            ymax = int(obj[6] * ih)
            # print(labels[index])
            cv.rectangle(src, (xmin, ymin), (xmax, ymax), (255, 195, 0), 2, 8)
            cv.putText(src, "person", (xmin, ymin-10), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 255), 1, 8)
    # cv.imshow("People Detection", src)
    base64 = cv2_base64(src)
    # print(base64.decode("ascii"))
    # cv.waitKey(0)
    return base64.decode("ascii"),peoplenumbers


if __name__ == "__main__":
    ssd_fimage_demo()

