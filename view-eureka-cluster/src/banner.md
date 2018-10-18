# banner更换
#### 制作banner：
从https://chars.surge.sh 网站上复制字符画，然后在src/main/resources目录下新建一个banner.txt文件，将字符画粘贴到该文件中。


#### 关闭banner：
1、main里的内容修改为：<br>
SpringApplication app = new SpringApplication(Ch522Application.class);<br>
app.setShowBanner(false);<br>
app.run(args);<br>
2、或使用fluent API修改为：<br>
new SpringApplicationBuilder(Ch522Application.class)<br>
            .showBanner(false)<br>
            .run(args);
