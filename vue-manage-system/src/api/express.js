import request from './request';

export const GetExpressInfo = query => {
    return request({
        url: 'express/',
        method: 'get',
        params: {
            pagenum: query.pageIndex,
        }
    });
};

export const ChangeExpressInfo = form => {
    return request({
        url: 'express/',
        method: 'put',
        data: form
    });
};

export const DeleteExpressInfo = id => {
    return request({
        url: 'express/' + id,
        method: 'delete'
    });
};

export const AddExpressInfo = form => {
    return request({
        url: 'express/',
        method: 'post',
        data: form
    });
};

export const SearchExpressInfo = id => {
    return request({
        url: 'express/' + id,
        method: 'get',
    });
};
