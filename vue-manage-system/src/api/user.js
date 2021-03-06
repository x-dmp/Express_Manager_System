import request from './request';

export const GetUserInfo = query => {
    return request({
        url: 'users/',
        method: 'get',
        params: {
            pagenum: query.pageIndex,
            query:query.username,
        }
    });
};

export const ChangeUserInfo = form => {
    return request({
        url: 'users/',
        method: 'put',
        data: form
    });
};

export const DeleteUserInfo = id => {
    return request({
        url: 'users/' + id,
        method: 'delete'
    });
};

export const AddUserInfo = form => {
    return request({
        url: 'users/',
        method: 'post',
        data: form
    });
};

export const Login = form => {
    return request({
        url: 'users/login/',
        method: 'post',
        data: form
    });
};

export const UpdateAvatar = (id, data) => {
    return request({
        url: 'users/'+id+'/avatar',
        method: 'post',
        data: data
    });
};
