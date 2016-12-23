using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using WCFService;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService" in both code and config file together.
[ServiceContract]
public interface IService
{
    [OperationContract]
    [WebGet(UriTemplate = "/Book", ResponseFormat = WebMessageFormat.Json)]
    string[] List();

    [OperationContract]
    [WebGet(UriTemplate = "/Book/{id}", ResponseFormat = WebMessageFormat.Json)]

    WCFBook GetBook(String id);

    [OperationContract]
    [WebGet(UriTemplate = "/Book/choose", ResponseFormat = WebMessageFormat.Json)]
    string[] ListChoice();

    [OperationContract]
    [WebGet(UriTemplate = "/Book/choose/{cg}", ResponseFormat = WebMessageFormat.Json)]

    List<WCFBook> GetBookByCg(String cg);

    [OperationContract]
    [WebInvoke(UriTemplate = "/Delete", Method = "POST",
        RequestFormat = WebMessageFormat.Json,
        ResponseFormat = WebMessageFormat.Json)]
    void Delete(WCFBook Book);



    [OperationContract]
    [WebInvoke(UriTemplate = "/Update", Method = "POST",
        RequestFormat = WebMessageFormat.Json,
        ResponseFormat = WebMessageFormat.Json)]
    void Update(WCFBook Book);

    [OperationContract]
    [WebInvoke(UriTemplate = "/Insert", Method = "POST",
        RequestFormat = WebMessageFormat.Json,
        ResponseFormat = WebMessageFormat.Json)]
    void Insert(WCFBook Book);
}

[DataContract]
public class WCFBook
{
    string id;
    string title;
    string description;
    double? price;
    string cg;
    string image;
    int? quantity;

    public static WCFBook Make(string id,string title,string description,double? price,
        string cg,string image,int? quantity)
    {
        WCFBook c = new WCFBook();
        c.id = id;
        c.title  = title;
        c.description = description;
        c.price = price;
        c.cg = cg;
        c.image = image;
        c.quantity = quantity;

        return c;
    }
    


    [DataMember]
    public string Id
    {
        get { return id; }
        set { id = value; }
    }

    [DataMember]
    public string Title
    {
        get { return title; }
        set { title = value; }
    }

    [DataMember]
    public string Description
    {
        get { return description; }
        set { description = value; }
    }

    [DataMember]
    public double? Price
    {
        get { return price; }
        set { price = value; }
    }

    [DataMember]
    public string Cg
    {
        get { return cg; }
        set { cg = value; }
    }

    [DataMember]
    public string Image
    {
        get { return image; }
        set { image = value; }
    }

    [DataMember]
    public int? Quantity
    {
        get { return quantity; }
        set { quantity = value; }
    }
    //public void Update(WCFBook c)
    //{
    //    Book book = new Book
    //    {
    //        id = c.Id;
    //    c.title = title;
    //    c.description = description;
    //    c.price = price;
    //    c.cg = cg;
    //    c.image = image;
    //    c.quantity = quantity;
    //};
    //Data.UpdateBook(book);
    //}
}