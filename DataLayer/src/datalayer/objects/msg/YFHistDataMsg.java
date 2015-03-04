// Generated by Cap'n Proto compiler, DO NOT EDIT
// source: C:\Users\chinmay.nerurkar\Desktop\ws\Scraper\DataLayer\capnp\schema\yfhistdata.capnp

package datalayer.objects.msg;

public final class YFHistDataMsg {
  public static class YFHistData {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)7,(short)1);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return YFHistData.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final boolean hasSymbol() {
        return !_pointerFieldIsNull(0);
      }
      public final org.capnproto.Text.Builder getSymbol() {
        return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
      }
      public final void setSymbol(org.capnproto.Text.Reader value) {
        _setPointerField(org.capnproto.Text.factory, 0, value);
      }
      public final void setSymbol(String value) {
        _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
      }
      public final org.capnproto.Text.Builder initSymbol(int size) {
        return _initPointerField(org.capnproto.Text.factory, 0, size);
      }
      public final int getDate() {
        return _getIntField(0);
      }
      public final void setDate(int value) {
        _setIntField(0, value);
      }

      public final double getOpen() {
        return _getDoubleField(1);
      }
      public final void setOpen(double value) {
        _setDoubleField(1, value);
      }

      public final double getHigh() {
        return _getDoubleField(2);
      }
      public final void setHigh(double value) {
        _setDoubleField(2, value);
      }

      public final double getLow() {
        return _getDoubleField(3);
      }
      public final void setLow(double value) {
        _setDoubleField(3, value);
      }

      public final double getClose() {
        return _getDoubleField(4);
      }
      public final void setClose(double value) {
        _setDoubleField(4, value);
      }

      public final long getAdv() {
        return _getLongField(5);
      }
      public final void setAdv(long value) {
        _setLongField(5, value);
      }

      public final double getAdjClose() {
        return _getDoubleField(6);
      }
      public final void setAdjClose(double value) {
        _setDoubleField(6, value);
      }

    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public boolean hasSymbol() {
        return !_pointerFieldIsNull(0);
      }
      public org.capnproto.Text.Reader getSymbol() {
        return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
      }

      public final int getDate() {
        return _getIntField(0);
      }

      public final double getOpen() {
        return _getDoubleField(1);
      }

      public final double getHigh() {
        return _getDoubleField(2);
      }

      public final double getLow() {
        return _getDoubleField(3);
      }

      public final double getClose() {
        return _getDoubleField(4);
      }

      public final long getAdv() {
        return _getLongField(5);
      }

      public final double getAdjClose() {
        return _getDoubleField(6);
      }

    }

  }



public static final class Schemas {
public static final org.capnproto.SegmentReader b_ed202a14277c2405 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u0005\u0024\u007c\u0027\u0014\u002a\u0020\u00ed" +
   "\u0055\u0000\u0000\u0000\u0001\u0000\u0007\u0000" +
   "\u00ea\u0037\u00b7\u0039\u00cd\u0083\u00d2\u0089" +
   "\u0001\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u0002\u0003\u0000\u0000" +
   "\u0041\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u003d\u0000\u0000\u0000\u00c7\u0001\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0043\u003a\\\u0055\u0073\u0065\u0072\u0073" +
   "\\\u0063\u0068\u0069\u006e\u006d\u0061\u0079" +
   "\u002e\u006e\u0065\u0072\u0075\u0072\u006b\u0061" +
   "\u0072\\\u0044\u0065\u0073\u006b\u0074\u006f" +
   "\u0070\\\u0077\u0073\\\u0053\u0063\u0072" +
   "\u0061\u0070\u0065\u0072\\\u0044\u0061\u0074" +
   "\u0061\u004c\u0061\u0079\u0065\u0072\\\u0063" +
   "\u0061\u0070\u006e\u0070\\\u0073\u0063\u0068" +
   "\u0065\u006d\u0061\\\u0079\u0066\u0068\u0069" +
   "\u0073\u0074\u0064\u0061\u0074\u0061\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0059\u0046\u0048" +
   "\u0069\u0073\u0074\u0044\u0061\u0074\u0061\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0020\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d1\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00cc\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00d8\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d5\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d0\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00dc\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0002\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d9\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d4\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00e0\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0003\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0003\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00dd\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00d8\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00e4\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0004\u0000\u0000\u0000\u0003\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0004\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00e1\u0000\u0000\u0000\"\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00dc\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00e8\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0005\u0000\u0000\u0000\u0004\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0005\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00e5\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00e0\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00ec\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0006\u0000\u0000\u0000\u0005\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0006\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00e9\u0000\u0000\u0000\"\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00e4\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00f0\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0007\u0000\u0000\u0000\u0006\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00ed\u0000\u0000\u0000\u004a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00ec\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u00f8\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0073\u0079\u006d\u0062\u006f\u006c\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0064\u0061\u0074\u0065\u0000\u0000\u0000\u0000" +
   "\u0008\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0008\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006f\u0070\u0065\u006e\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0068\u0069\u0067\u0068\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006c\u006f\u0077\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006c\u006f\u0073\u0065\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0061\u0064\u0076\u0000\u0000\u0000\u0000\u0000" +
   "\u0009\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0009\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0061\u0064\u006a\u0043\u006c\u006f\u0073\u0065" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000b\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
}
}

