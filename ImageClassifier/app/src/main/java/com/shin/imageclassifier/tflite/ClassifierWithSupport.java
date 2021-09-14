package com.shin.imageclassifier.tflite;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

import static org.tensorflow.lite.support.image.ops.ResizeOp.ResizeMethod.NEAREST_NEIGHBOR;

public class ClassifierWithSupport {
    private static final String MODEL_NAME = "mobilenet_imagenet_model.tflite";
    private static final String LABEL_FILE = "labels.txt.";

    Context context;
    Interpreter interpreter;
    TensorBuffer outputBuffer;
    TensorImage inputImage;
    int modelInputWidth, modelInputHeight, modelInputChannel;
    private List<String> labels;

    public ClassifierWithSupport(Context context) {
        this.context = context;
    }

    public void init() throws IOException {
        ByteBuffer model = FileUtil.loadMappedFile(context, MODEL_NAME);
        model.order(ByteOrder.nativeOrder());
        interpreter = new Interpreter(model);

        initModelShape();
        labels = FileUtil.loadLabels(context, LABEL_FILE);
    }

    private void initModelShape() {
        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] shape = inputTensor.shape();
        modelInputChannel = shape[0];
        modelInputWidth = shape[1];
        modelInputHeight = shape[2];

        inputImage = new TensorImage(inputTensor.dataType());

        Tensor outputTensor = interpreter.getOutputTensor(0);
        outputBuffer = TensorBuffer.createFixedSize(outputTensor.shape(), outputTensor.dataType());
    }

    private Bitmap convertBitmapToARGB8888(Bitmap bitmap) {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true);
    }

    public Pair<Integer, Float> classify(Bitmap image) {
        inputImage = loadImage(image);
        interpreter.run(inputImage.getBuffer(), outputBuffer.getBuffer().rewind());

        Map<String, Float> output =
                new TensorLabel(labels, outputBuffer).getMapWithFloatValue();

        return argmax(output);
    }

    private Pair<String, Float> argmax(Map<String, Float> map) {
        String maxKey="";
        float maxVal = -1;

        for(Map.Entry<String, Float> entry : map.entrySet()) {
            float f = entry.getValue();
            if(f > maxVal) {
                maxKey = entry.getKey();
                maxVal = f;
            }
        }
        return new Pair<>(maxKey, maxVal);
    }



    private TensorImage loadImage(final Bitmap bitmap) {
        inputImage.load(bitmap);

        ImageProcessor imageProcessor =
                new ImageProcessor.Builder()
                .add(new ResizeOp(modelInputWidth, modelInputHeight, NEAREST_NEIGHBOR))
                .add(new NormalizeOp(0.0f, 255.0f))
                .build();
        return imageProcessor.process(inputImage);
    }

}
