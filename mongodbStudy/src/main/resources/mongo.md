db.letter.insertMany([
{name:'David'},
{name:'DavId'},
{name:'DAVID'}
]);
// 不区分大小写查询
db.letter.find({name:/^david$/i}).pretty();