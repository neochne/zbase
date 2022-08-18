package com.zbase.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public final class FileProgressRequestBody extends RequestBody {

    private final RequestBody oriRequestBody;

    private final String fileName;

    private final HttpCallback httpCallback;

    public FileProgressRequestBody(RequestBody oriRequestBody, String fileName, HttpCallback httpCallback) {
        this.oriRequestBody = oriRequestBody;
        this.fileName = fileName;
        this.httpCallback = httpCallback;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return oriRequestBody.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return oriRequestBody.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {
        BufferedSink sink = Okio.buffer(new CountingSink(bufferedSink));
        oriRequestBody.writeTo(sink);
        sink.flush();
    }

    private final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NonNull Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            httpCallback.onProgress((int) (100F * bytesWritten / contentLength()),fileName,contentLength());
        }

    }

}
