import request from './request';

export const GetSendInfo = query => {
    return request({
        url: 'sends/',
        method: 'get',
        params: {
            pagenum: query.pageIndex,
            query:query.uuid,
        }
    });
};

export const ChangeSendInfo = form => {
    return request({
        url: 'sends/',
        method: 'put',
        data: form
    });
};

export const DeleteSendInfo = uuid => {
    return request({
        url: 'sends/' + uuid,
        method: 'delete'
    });
};

export const AddSendInfo = form => {
    return request({
        url: 'sends/',
        method: 'post',
        data: form
    });
};

// export const Login = form => {
//     return request({
//         url: 'users/login/',
//         method: 'post',
//         data: form
//     });
// };
//
// export const UpdateAvatar = (id, data) => {
//     return request({
//         url: 'users/'+id+'/avatar',
//         method: 'post',
//         data: data
//     });
// };
