Ext.require(['Ext.data.*', 'Ext.grid.*']);

Ext.define('Lime', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'int', //TODO
        useNull: true
    }, 'model', 'batteryLevel', 'lat', 'lon'],
    validations: [{
        type: 'length',
        field: 'model',
        min: 1
    }, {
        type: 'length',
        field: 'batteryLevel',
        min: 1
    }, {
        type: 'length',
        field: 'lat',
        min: 1
    }, {
        type: 'length',
        field: 'lon',
        min: 1
    }]
});

Ext.onReady(function(){

    var store = Ext.create('Ext.data.Store', {
        autoLoad: true,
        autoSync: true,
        model: 'Lime',
        proxy: {
            type: 'rest',
            url: '/limes',
            reader: {
                type: 'json',
                rootProperty: 'data'
            },
            writer: {
                type: 'json'
            }
        },
        listeners: {
            write: function(store, operation){
                var record = operation.getRecords()[0],
                    name = Ext.String.capitalize(operation.action),
                    verb;
                    
                    
                if (name == 'Destroy') {
                    verb = 'Destroyed';
                } else {
                    verb = name + 'd';
                }
                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
                
            }
        }
    });
    
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        listeners: {
            cancelEdit: function(rowEditing, context) {
                // Canceling editing of a locally added, unsaved record: remove it
                if (context.record.phantom) {
                    store.remove(context.record);
                }
            }
        }
    });
    
    var grid = Ext.create('Ext.grid.Panel', {
        renderTo: document.body,
        plugins: [rowEditing],
        width: 500,
        height: 330,
        frame: true,
        title: 'Limes',
        store: store,
        iconCls: 'icon-user',
        columns: [{
            text: 'ID',
            width: 50,
            sortable: true,
            dataIndex: 'id',
            renderer: function(v, meta, rec) {
                return rec.phantom ? '' : v;
            }
        }, {
            text: 'model',
            flex: 1,
            sortable: true,
            dataIndex: 'model',
            field: {
                xtype: 'textfield'
            }
        }, {
            header: 'Battery Level',
            width: 120,
            sortable: true,
            dataIndex: 'batteryLevel',
            field: {
                xtype: 'textfield'
            }
        }, {
            text: 'Lat',
            width: 120,
            sortable: true,
            dataIndex: 'lat',
            field: {
                xtype: 'textfield'
            }
        }, {
            text: 'Lon',
            width: 120,
            sortable: true,
            dataIndex: 'lon',
            field: {
                xtype: 'textfield'
            }
        }],
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function(){
                    // empty record
                    var rec = new Lime();
                    store.insert(0, rec);
                    rowEditing.startEdit(rec, 0);
                }
            }, '-', {
                itemId: 'delete',
                text: 'Delete',
                iconCls: 'icon-delete',
                disabled: true,
                handler: function(){
                    var selection = grid.getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                        store.remove(selection);
                    }
                }
            }]
        }]
    });
    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
        grid.down('#delete').setDisabled(selections.length === 0);
    });
});
