$(function () {
    var init = function () {
        initBuyBtn();
        $('#addToCard').click(addProductToCart);
        $('#addProductPopup .count').change(calculateCost);
        $('#loadMore').click(loadMoreProducts);
        $('#loadMoreMyOrders').click(loadMoreMyOrders);
        initSearchForm();
        $('#goSearch').click(goSearch);
        $('.remove-product').click(removeProductFromCart);
        $('.post-request').click(function () {
            postRequest($(this).attr('data-url'));
        });
    };

    $("#id ").keypress(function (event) {
        event = event || window.event;
        if (event.charCode && event.charCode != 0 && (event.charCode < 48 || event.charCode > 57))
            return false;
    });
    
    $("#price ").keypress(function (event) {
        event = event || window.event;
        if (event.charCode && event.charCode != 0 && (event.charCode < 48 || event.charCode > 57))
            return false;
    });
    
    $("#id_pr ").keypress(function (event) {
        event = event || window.event;
        if (event.charCode && event.charCode != 0 && (event.charCode < 48 || event.charCode > 57))
            return false;
    });

    var loadMoreMyOrders = function () {
        var btn = $('#loadMoreMyOrders');
        convertButtonToLoader(btn, 'btn-outline');
        var pageNumber = parseInt($('#myOrders').attr('data-page-number'));
        var url = '/ajax/html/more/my-orders?page=' + (pageNumber + 1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#myOrders tbody').append(html);
                pageNumber++;
                var pageCount = parseInt($('#myOrders').attr('data-page-count'));
                $('#myOrders').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-outline', loadMoreMyOrders);
                } else {
                    btn.remove();
                }
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-outline', loadMoreMyOrders);
                if (xhr.status == 401) {
                    window.location.href = '/sign-in';
                } else {
                    alert('Error');
                }
            }
        });
    };

    var showAddProductPopup = function () {
        var idProduct = $(this).attr('data-id-product');
        var product = $('#product' + idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
        $('#addProductPopup .name').text(product.find('.name').text());
        var price = product.find('.price').text();
        $('#addProductPopup .price').text(price);
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup .producer').text(product.find('.producer').text());
        $('#addProductPopup .count').val(1);
        $('#addProductPopup .cost').text(price);
        $('#addToCart').removeClass('hidden');
        $('#addToCardIndicator').addClass('hidden');
        $('#addProductPopup').modal({
            show: true
        });
    };

    var initBuyBtn = function () {
        $('.buy-btn').click(showAddProductPopup);
    };

    var postRequest = function (url) {
        var form = '<form id="postRequestForm" action="' + url + '" method="post"></form>';
        $('body').append(form);
        $('#postRequestForm').submit();
    };

    var addProductToCart = function () {
        var idProduct = $('#addProductPopup').attr('data-id-product');
        var count = $('#addProductPopup .count').val();
        $('#addToCart').addClass('hidden');
        $('#addToCardIndicator').removeClass('hidden');
        var btn = $('#addToCart');
        convertButtonToLoader(btn, 'btn-outline');

        $.ajax({
            url: '/ajax/json/product/add',
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                $('#currentShoppingCart .total-count').text(data.totalCount);
                $('#currentShoppingCart .total-cost').text(data.totalCost);
                $('#currentShoppingCart').removeClass('hidden');
                $('#addProductPopup').modal('hide');
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-outline', addProductToCart);
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });

    };

    var calculateCost = function () {
        var priceStr = $('#addProductPopup .price').text();
        var price = parseFloat(priceStr.replace('$', ' '));
        var count = parseInt($('#addProductPopup .count').val());
        var min = parseInt($('#addProductPopup .count').attr('min'));
        var max = parseInt($('#addProductPopup .count').attr('max'));
        if (count >= min && count <= max) {
            var cost = price * count;
            $('#addProductPopup .cost').text('$ ' + cost);
        } else {
            $('#addProductPopup .count').val(1);
            $('#addProductPopup .cost').text(priceStr);
        }
    };

    var convertButtonToLoader = function (btn, btnClass) {
        btn.removeClass(btnClass);
        btn.removeClass('btn');
        btn.addClass('load-indicator');
        var text = btn.text();
        btn.text('');
        btn.attr('data-btn-text', text);
        btn.off('click');
    };
    var convertLoaderToButton = function (btn, btnClass, actionClick) {
        btn.removeClass('load-indicator');
        btn.addClass('btn');
        btn.addClass(btnClass);
        btn.text(btn.attr('data-btn-text'));
        btn.removeAttr('data-btn-text');
        btn.click(actionClick);
    };

    var loadMoreProducts = function () {
        var btn = $('#loadMore');
        convertButtonToLoader(btn, 'btn-outline-warning');
        var pageNumber = parseInt($('#productList').attr('data-page-number'));
        var url = '/ajax/html/more' + location.pathname + '?page=' + (pageNumber + 1) + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#productList .row').append(html);
                pageNumber++;
                var pageCount = parseInt($('#productList').attr('data-page-count'));
                $('#productList').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-outline-warning', loadMoreProducts);
                } else {
                    btn.remove();
                }
                initBuyBtn();
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-outline-warning', loadMoreProducts);
                alert('Error');
            }
        });
    };

    var initSearchForm = function () {
        $('#allCategories').click(function () {
            $('.categories .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.categories .search-option').click(function () {
            $('#allCategories').prop('checked', false);
        });
        $('#allProducers').click(function () {
            $('.producers .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.producers .search-option').click(function () {
            $('#allProducers').prop('checked', false);
        });
    };

    var goSearch = function () {
        var isAllSelected = function (selector) {
            var unchecked = 0;
            $(selector).each(function (index, value) {
                if (!$(value).is(':checked')) {

                    unchecked++;
                }
            });
            return unchecked === 0;
        };
        if (isAllSelected('.categories .search-option')) {
            $('.categories .search-option').prop('checked', false);
        }
        if (isAllSelected('.producers .search-option')) {
            $('.producers .search-option').prop('checked', false);
        }
        $('form.search').submit();
    };

    var confirm = function (msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };

    var removeProductFromCart = function () {
        var btn = $(this);
        confirm('Are you sure?', function () {
            executeRemoveProduct(btn);
        });
    };

    var refreshTotalCost = function () {
        var total = 0;
        $('#shoppingCart .item').each(function (index, value) {
            var count = parseInt($(value).find('.count').text());
            var price = parseFloat($(value).find('.price').text().replace('$', ' '));
            total += price * count;
        });
        $('#shoppingCart .total').text('$' + total);
    }

    var executeRemoveProduct = function (btn) {
        var idProduct = btn.attr('data-id-product');
        var count = btn.attr('data-count');
        convertButtonToLoader(btn, 'btn-outline');
        $.ajax({
            url: '/ajax/json/product/remove',
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                if (data.totalCount == 0) {
                    window.location.href = '/products';
                } else {
                    var prevCount = parseInt($('#product' + idProduct + ' .count').text());
                    var remCount = parseInt(count);
                    if (remCount >= prevCount) {
                        $('#product' + idProduct).remove();
                    } else {
                        convertLoaderToButton(btn, 'btn-outline', removeProductFromCart);
                        $('#product' + idProduct + ' .count').text(prevCount - remCount);
                        if (prevCount - remCount == 1) {
                            $('#product' + idProduct + ' a.remove-all').remove();
                        }
                    }
                    refreshTotalCost();
                }
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-outline', removeProductFromCart);
                alert('Error');
            }
        });
    }




    init();
});

