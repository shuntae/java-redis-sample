package jp.co.introduction.redis.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import jp.co.introduction.redis.model.ItemModel;

/**
 * RedisImplの使い方兼テストコード<br>
 * とりあえずCtrl＋F11で実行してコンソールを見ること
 */
public class RedisImplTest {

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() {
        System.out.println("########## [START] ########## : " + testName.getMethodName());
    }

    @After
    public void after() {
        System.out.println("########## [E N D] ########## : " + testName.getMethodName());
        System.out.println("\n");
    }

    /**
     * 文字列をキャッシュさせるケース
     */
    @Test
    public void test1() {
        // RedisのImplのインスタンス生成
        RedisImpl<String> hoge = new RedisImpl<String>(String.class);
        // キャッシュキー
        String key = "neko";
        // キャッシュする値
        String value = "ブリティッシュショートヘア";

        // キャッシュキーの設定
        hoge.setUp(key);
        // キャッシュの格納
        hoge.set(value);

        // 格納したキャッシュを取得
        String getValue = hoge.get();
        // 格納したキャッシュの出力
        System.out.println("★Redisから取得したデータ : " + getValue);
    }

    /**
     * nullをキャッシュさせるケース
     */
    @Test
    public void test2() {
        // RedisのImplのインスタンス生成
        RedisImpl<String> hoge = new RedisImpl<String>(String.class);
        // キャッシュキー
        String key = "nullData";
        // キャッシュする値
        String value = null;

        // キャッシュキーの設定
        hoge.setUp(key);
        // キャッシュの格納
        hoge.set(value);

        // 格納したキャッシュを取得
        String getValue = hoge.get();
        System.out.println("★Redisから取得したデータ : " + getValue);
    }

    /**
     * 複数のキーでキャッシュさせるケース
     */
    @Test
    public void test3() {
        // RedisのImplのインスタンス生成
        RedisImpl<String> hoge = new RedisImpl<String>(String.class);
        // キャッシュキー
        String key = "item";
        // キャッシュキー(optional)1
        String itemCode = "1234567";
        // キャッシュキー(optional)2
        Integer lowerPrice = 100;
        // キャッシュキー(optional)2
        Integer upperPrice = 500;
        // キャッシュキー(optional)3
        List<String> freeword = Arrays.asList("電池", "アルカリ");
        // キャッシュキー(optional)4
        List<String> brandList = Arrays.asList("パナソニック", "ダイソー");
        // キャッシュする値
        String value = "アルカリ性単三電池8本セット";

        // キャッシュキーの設定
        hoge.setUp(key, itemCode, lowerPrice, upperPrice, freeword, brandList);
        // キャッシュの格納
        hoge.set(value);

        // 格納したキャッシュを取得
        String getValue = hoge.get();
        System.out.println("★Redisから取得したデータ : " + getValue);
    }

    /**
     * 構造体(オブジェクト/モデル)をキャッシュさせるケース
     */
    @Test
    public void test4() {
        // RedisのImplのインスタンス生成
        RedisImpl<ItemModel> hoge = new RedisImpl<ItemModel>(ItemModel.class);
        // キャッシュキー
        String key = "model";
        // キャッシュする値
        ItemModel value = new ItemModel();
        value.setItemCode("111111");
        value.setItemName("チャオチュール");
        value.setShopCode("SKU222");
        value.setShopName("inaba");

        // キャッシュキーの設定
        hoge.setUp(key);
        // キャッシュの格納
        hoge.set(value);

        // 格納したキャッシュを取得
        ItemModel getValue = hoge.get();
        System.out.println("★Redisから取得したデータ : " + getValue);
    }
}