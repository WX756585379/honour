// 本机开发时使用
var WxApiRoot = 'http://localhost:8087';
// 局域网测试使用
// var WxApiRoot = 'http://192.168.0.101:8080';
// 云平台上线时使用
// var WxApiRoot = 'https://www.menethil.com.cn';

module.exports = {
  ExchangeOpenIdUrl: WxApiRoot + '/weixin/exchangeOpenId', //微信code换取openId接口
  CatalogList: WxApiRoot + 'catalog/index', //分类目录全部分类数据接口
  CatalogCurrent: WxApiRoot + 'catalog/current', //分类目录当前分类数据接口
};