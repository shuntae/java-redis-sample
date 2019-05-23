# java-redis-sample
## example code
    // new instance
    RedisImpl<ItemModel> hoge = new RedisImpl<ItemModel>(ItemModel.class);
    // cache key
    String key = "model";
    // optional key(n)
    String genre = "food";
    // cache value
    ItemModel value = new ItemModel();
    value.setItemCode("111111");
    value.setItemName("ciao churu");
    value.setShopCode("SKU222");
    value.setShopName("inaba");
    
    // setup(gerate key)
    hoge.setUp(key, genre);
    
    // set cache
    hoge.set(value);
    
    // get cache
    ItemModel getValue = hoge.get();