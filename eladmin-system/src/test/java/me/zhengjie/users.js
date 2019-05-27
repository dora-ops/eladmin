import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/users',
    method: 'post',
    data
  })
}

export function queryAll() {
  return request({
  url: 'api/queryAll/users',
  method: 'get',
  })
}

export function findById() {
  return request({
  url: 'api/findById/users',
  method: 'get',
  })
}

export function del(id) {
  return request({
    url: 'api/users/' + id,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/users',
    method: 'put',
    data
  })
}
