package com.st00.afir.emojifyme;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by zakaria_afir on 12/12/2017.
 */

public class Emojifier {

    private static final String LOG_TAG = Emojifier.class.getSimpleName();

    /**
     * Method for detecting faces in a bitmap.
     *
     * @param context The application context.
     * @param picture The picture in which to detect the faces.
     */
    public static void detectFaces(Context context, Bitmap picture) {

        // Create the face detector, disable tracking and enable classifications
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        // Build the frame
        Frame frame = new Frame.Builder().setBitmap(picture).build();

        // Detect the faces
        SparseArray<Face> faces = detector.detect(frame);

        // Log the number of faces
        Log.d(LOG_TAG, "detectFaces: number of faces = " + faces.size());

        // If there are no faces detected, show a Toast message
        if (faces.size() == 0) {
            Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
        } else {
            //it detects faces in landscape mode only
            Toast.makeText(context, " Number of faces = " + faces.size(), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < faces.size(); ++i) {
                Face face = faces.valueAt(i);

                // Log the classification probabilities for each face.
                getClassifications(face);
            }
        }

        // Release the detector
        detector.release();
    }

    /**
     * Method for logging the classification probabilities.
     *
     * @param face The face to get the classification probabilities.
     */
    private static void getClassifications(Face face) {
        // Log all the probabilities
        Log.d(LOG_TAG, "getClassifications: Smiling probability = " + face.getIsSmilingProbability());
        Log.d(LOG_TAG, "getClassifications: Left eye open probability = "
                + face.getIsLeftEyeOpenProbability());
        Log.d(LOG_TAG, "getClassifications: Right eye open probability = "
                + face.getIsRightEyeOpenProbability());
        Log.d(LOG_TAG, "*********************************************************************");

    }
}
