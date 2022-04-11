
class DataTable {
    element;
    headers;
    items;
    copyItems;
    selected;
    pagination;
    numberOfEntries;

    constructor(selector) {
        this.element = document.querySelector(selector);
        this.headers = []
        this.items = []
        this.pagination = {total: 0,
            noItemsPage: 0,
            noPages: 0,
            actual: 0,
            pointer: 0,
            diff: 0,
            lastPagesBeforeDots: 0}

        this.selected = []
        this.numberOfEntries = 5

    }

    parse() {
        const headers = [...this.element.querySelector('thead tr').children]
        const trs = [...this.element.querySelector('tbody').children]

        headers.forEach(element => {
            this.headers.push(element.textContent)
        })

        trs.forEach(tr => {
            const cells = [...tr.children]

            const item = {
                id: this.generateUUID(), //generamos un id especifico para no tener problemas al seleccionar 
                values: []
            }
            cells.forEach(cell => {
                if (cell.children.length > 0) {
                    //const status = [...cell.children[0].getAttribute('class')] 
                    const statusElement = [...cell.children][0]
                    const status = statusElement.getAttribute('class')
                    if (status !== null) {

                        item.values.push(`<span class='${status}'></span>`)

                    }
                } else {
                    item.values.push(cell.textContent)
                }
            })

            this.items.push(item)

        })

        this.makeTable()


    }

    makeTable() {
        this.copyItems = [...this.items]

        this.initPagination(this.items.length, this.numberOfEntries)

        const container = document.createElement('div')
        container.id = this.element.id
        this.element.innerHTML = ''
        this.element.replaceWith(container)
        this.element = container

        this.createHTML()
        this.renderHeaders()
        this.renderSearch()
        this.renderRows()
        this.renderPagesButtons()
        this.renderSelectEntries()
    }

    initPagination(total, entrie) {
        this.pagination.total = total
        this.pagination.noItemsPage = entrie
        this.pagination.noPages = Math.ceil(this.pagination.total / this.pagination.noItemsPage)
        this.pagination.actual = 1;
        this.pagination.pointer = 0;
        this.pagination.diff = this.pagination.noItemsPage - (this.pagination.total % this.pagination.noItemsPage)
    }

    //id para cada elemento
    generateUUID() {
        return (Date.now() * Math.floor(Math.random() * 100000)).toString()
    }

    createHTML() {

        this.element.innerHTML = `
                <div class="datatable-container">


            <div class="search">
                <input type="text" name="" class="search-input">
            </div>
        
        <table id="datatable" class="datatable">
            <thead>
                <tr>
        
                </tr>
            </thead>
        
        
        <tbody>
        <td>
            <select></select>
        </td>
        </tbody>
        </table>
        
                    <div class="footer-tools">
                <div class="list-items">
                    <select name="n-entries" id="n-entries" class="n-entries">
                    </select>
                    Productos
                </div>

                <div class="pages">

                </div>
            </div>
        
        </div>
        `
    }
    
    renderHeaders(){
        this.element.querySelector('thead tr').innerHTML=''
        
        this.headers.forEach(header=>{
            this.element.querySelector('thead tr').innerHTML += `<tr>${header}</tr>`
        })
    }
    
    renderRows(){
        this.element.querySelector('tbody').innerHTML=''
        let i=0
        const {pointer, total} = this.pagination
        const limit = this.pagination.actual * this.pagination.noItemsPage
        
        for(i=pointer; i<limit; i++){
            if(i===total){
                break;
            }
            
            const{id, values}= this.copyItems[i]
            const checked = this.isChecked(id)
            
            
            let data = ''
            
            data += `<td class='table-checkbox'>
                        <input type='checkbox' class='datatable-checkbox' data-id='${id}'  ${checked? "checked" : ""}>

                </td>`
            
            values.forEach(cell=>{
                data+=`<td>${cell}</td>`
                data +=`<td><img></td>`
                data +=`
                            
                                <option>${cell}</option>
                            
                        `
            })
            
            this.element.querySelector('tbody').innerHTML+=`<tr>${data}</tr>`
            
        }
    }
    
        renderSearch(){
            
        }
        renderPagesButtons(){
            
        }
        renderSelectEntries(){
            
        }
    
    isChecked(){
        
        const items = this.selected
        let res = false
        
        if(items.length===0) return false;
        
        items.forEach(item=>{
            if(item.id===id) res = true
        })
        
        return res
    }

}

