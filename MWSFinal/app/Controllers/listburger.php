<?php

namespace App\Controllers;

use CodeIgniter\API\ResponseTrait;
use CodeIgniter\RESTful\ResourceController;

class listburger extends ResourceController {

use ResponseTrait;

    protected $modelName ='App\Models\listBurgerModel';
    protected $format ='json';

     # construct adalah sebuah function yang saat class aplikasinya dijalankan
    # maka fungsi ini akan ikut langsung dijalankan. 
    #function __construct()
    #{
        # dalam hal ini tidak akan digunakan
    #}

    public function index()
    {
        $data = $this->model->orderBy('ID', 'asc')->findAll();
        return $this->respond($data, 200);
    }

    public function show($ID = null)
    {
        $data = $this->model->where('ID', $ID)->find();
        if($data){
            return $this->respond($data, 200);
        }else{
            return $this->failNotFound("Data tidak ada!");
        }
    }

    public function create()
    {
        $data = $this->request->getPOST();
        $save = $this->model->save($data);

        if(!$save){
            return $this->fail($this->model->errors());
        }
        elseif($save){
            $respond=[

                'status' => 201,
                'error' => null,
                'message' => [
                    'success' => 'Data berhasil ditambahkan'
                ]

            ];
            return $this->respond($respond);

        }
    }

    public function update($ID = null)
    {
        $data = $this->request->getRawInput();  /* mengambil data yang baru diinputkan dan menyimpanya */
        $data['ID'] = $ID;
        
        $check_data = $this->model->where('id', $ID)->find();   /* Mencari data berdasarkan id dari inputan dengan id yang ada didalam database */
        
        if(!$check_data){   /* memeriksa apakah id dari inputan sama/ada didalam database */ 
            return $this->failNotFound("Data tidak ditemukan!");
        }
        
        $save = $this->model->save($data);  /* Menyimpan data inputan dari variabel $data */
        
        if(!$save){ 
            return $this->fail($this->model->errors());
        }

        $respond = [ /* Tampilan Outputan yang bisa dilihat pengguna saat proses update data berhasil/tidak ada error */
            'status' => '200',
            'error' => null,
            'message'=> [
                'success' => 'Data berhasil diperbarui!'
            ]
        ];
        return $this->respond($respond); /* Output yang ditampilkan */
    }

    public function delete($ID = null)
    {
        $check_data = $this->model->where('ID', $ID)->find();
        if($check_data){
            $this->model->delete($ID);
            $respond = [
                'status' => '200',
                'error' => null,
                'message'=> [
                    'success' => 'Data berhasil dihapus!'
                ]
            ];
            return $this->respond($respond); 
        }
        else{
            return $this->failNotFound("Data tidak ditemukan!");
        }
    }
    
}