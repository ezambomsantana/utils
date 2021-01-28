package com.bah.ai.api.jobrunner;

import com.bah.ai.api.jobrunner.dataconnector.aws.AwsS3GenericDataSource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public class Teste {

    public static void main(String args[]) {

        S3Client s3Client = S3Client.builder()
            .credentialsProvider(AwsS3GenericDataSource.buildAwsCredentialsProvider("acces-key", "secret-key"))
            .region(Region.of("us-east-1")).build();

        String bucket = "bucket";
        String key = "input/teste/";


        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucket).prefix(key).build();

        ListObjectsV2Response result = s3Client.listObjectsV2(request);
        List<S3Object> objects = result.contents();

        if (objects.isEmpty()) {
            System.out.println("nada encontrado");
        }

        for (S3Object object : objects) {

            String [] names = object.key().split("/");
            System.out.println("Downloading file " + object.key() + " from bucket " + bucket + " name");
        }

    }
}
