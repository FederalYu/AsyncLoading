# AsyncLoading

model one : asynctaskusingimagecache ：
1. 使用了AsyncTask实现异步加载。
2. 实现了ListView滑动时不加载图片，停止滑动并且手指离开屏幕后加载当前页面上的图片。
3. 实现了图片的三级缓存（内存、SD卡、网络）。

model two : asyncloadingusingvolley :
1. 使用了Volley网络框架实现异步加载。
2. 使用了RecyclerView实现包含图片的列表。
3. 加载图片等网络操作均由Volley框架完成。
