(function ($, window) {
    $.fn.baiduPage = function (config) {
        // 默认配置
        var defaults = {
            pageSize: 10,
            count: 100,
            current: 1,
            prevDes: "上一页",
            nextDes: "下一页",
            updateSelf: true,
            callback: null
        };
        // 插件配置合并
        this.oConfig = $.extend(defaults, config);
        var self = this;

        // 初始化函数
        var init = function () {
            // 初始化数据
            updateConfig(self.oConfig);
            // 事件绑定
            bindEvent();
        };
        // 更新方法
        var updateConfig = function (config) {
            typeof config.count !== 'undefined' ? self.count = config.count : self.count = self.oConfig.count;
            typeof config.pageSize !== 'undefined' ? self.pageSize = config.pageSize : self.pageSize = self.oConfig.pageSize;
            typeof config.current !== 'undefined' ? self.current = config.current : self.current = self.oConfig.current;
            self.pageCount = Math.ceil(self.count / self.pageSize);
            format();
        };
        var format = function () {
            var current = self.current;
            var count = self.pageCount;
            var html = '<div class="page-container"><ul>';
            if (current != 1)
                html += '<li class="page-item page-prev page-action-text">' + self.oConfig.prevDes + '</li>';
            var start = 1;
            var end = count;
            if (count > 10) {
                if (current <= 5) {
                    start = 1;
                    end = 10;
                } else if (current >= count - 4) {
                    start = count - 9;
                    end = count;
                } else {
                    start = current - 5;
                    end = current + 4;
                }
            }
            for (var i = start; i <= end; i++) {
                html += getItem(i);
            }
            if (current != count)
                html += '<li class="page-item page-next page-action-text">' + self.oConfig.nextDes + '</li>';
            html += '</ul></div>';
            self.html(html);
        };
        var getItem = function (i) {
            var item = '';
            var current = (i == self.current);
            item += '<li class="page-item" data-page="' + i + '"><div class="page-icon-content">';
            if (current) {
                item += '<div class="page-icon-current page-icon-content"></div>';
                item += '</div><span class="page-text-current">' + i + '</span></li>';
            } else {
                item += '<div class="' + (i % 2 == 0 ? 'page-icon-type1' : 'page-icon-type2') + ' page-icon"></div>';
                item += '</div><span class="page-text">' + i + '</span></li>';
            }
            return item;
        };
        //点击事件
        var bindEvent = function () {
            self.on('click', '.page-item', function () {
                var current;
                if ($(this).hasClass('page-prev')) {
                    current = Math.max(1, self.current - 1);
                } else if ($(this).hasClass('page-next')) {
                    current = Math.min(self.pageCount, self.current + 1);
                } else {
                    current = parseInt($(this).data('page'));
                }
                self.oConfig.callback && self.oConfig.callback(current);
                if (self.oConfig.updateSelf) {
                    self.current = current;
                    format();
                }
            })
        };
        // 启动
        init();
        //对外提供更新方法
        this.update = function (config) {
            updateConfig(config);
        };
        // 链式调用
        return self;
    };
})(jQuery, window);