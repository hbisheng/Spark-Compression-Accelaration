package org.sparkAligner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;

public class Lz77FPGACodec implements Configurable, CompressionCodec {
    private static final Log LOG = LogFactory.getLog(Lz77FPGACodec.class);
    
    private Configuration conf;
    public Lz77FPGACodec() {
        this(new Configuration());
    }

    public Lz77FPGACodec(final Configuration conf) {
        this.conf = conf;
    }

    @Override
    public CompressionOutputStream createOutputStream(final OutputStream out) throws IOException {
        return createOutputStream(out, null);
    }

    @Override
    public CompressionOutputStream createOutputStream(final OutputStream out, final Compressor compressor)
            throws IOException {
        LOG.info("Creating compressor stream");
        
        return new OStreamDelegatingCompressorStream(new Lz77FPGAOutputStream(out));
    }

    @Override
    public Class<? extends Compressor> getCompressorType() {
        LOG.warn("This codec doesn't use a compressor, returning null.");
        return null;
    }

    @Override
    public Compressor createCompressor() {
        LOG.warn("This codec doesn't use a compressor, returning null.");
        return null;
    }

    @Override
    public CompressionInputStream createInputStream(final InputStream in) throws IOException {
        return createInputStream(in, null);
    }

    @Override
    public CompressionInputStream createInputStream(final InputStream in, final Decompressor decompressor)
            throws IOException {
        LOG.debug("Creating decompressor stream");
        return new IStreamDelegatingDecompressorStream(new Lz77FPGAInputStream(in));
    }

    @Override
    public Class<? extends Decompressor> getDecompressorType() {
        LOG.warn("This codec doesn't use a decompressor, returning null.");
        return null;
    }

    @Override
    public Decompressor createDecompressor() {
        LOG.warn("This codec doesn't use a decompressor, returning null.");
        return null;
    }

    @Override
    public String getDefaultExtension() {
        return ".sz";
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    @Override
    public void setConf(final Configuration conf) {
        this.conf = conf;
    }
}

