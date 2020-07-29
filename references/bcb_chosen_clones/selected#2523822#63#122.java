    public synchronized void write() throws IOException {
        ZipOutputStream jar = new ZipOutputStream(new FileOutputStream(jarPath));
        int index = className.lastIndexOf('.');
        String packageName = className.substring(0, index);
        String clazz = className.substring(index + 1);
        String directory = packageName.replace('.', '/');
        ZipEntry dummyClass = new ZipEntry(directory + "/" + clazz + ".class");
        jar.putNextEntry(dummyClass);
        ClassGen classgen = new ClassGen(getClassName(), "java.lang.Object", "<generated>", Constants.ACC_PUBLIC | Constants.ACC_SUPER, null);
        byte[] bytes = classgen.getJavaClass().getBytes();
        jar.write(bytes);
        jar.closeEntry();
        ZipEntry synthFile = new ZipEntry(directory + "/synth.xml");
        jar.putNextEntry(synthFile);
        Comment comment = new Comment("Generated by SynthBuilder from L2FProd.com");
        Element root = new Element("synth");
        root.addAttribute(new Attribute("version", "1"));
        root.appendChild(comment);
        Element defaultStyle = new Element("style");
        defaultStyle.addAttribute(new Attribute("id", "default"));
        Element defaultFont = new Element("font");
        defaultFont.addAttribute(new Attribute("name", "SansSerif"));
        defaultFont.addAttribute(new Attribute("size", "12"));
        defaultStyle.appendChild(defaultFont);
        Element defaultState = new Element("state");
        defaultStyle.appendChild(defaultState);
        root.appendChild(defaultStyle);
        Element bind = new Element("bind");
        bind.addAttribute(new Attribute("style", "default"));
        bind.addAttribute(new Attribute("type", "region"));
        bind.addAttribute(new Attribute("key", ".*"));
        root.appendChild(bind);
        doc = new Document(root);
        imagesToCopy = new HashMap();
        ComponentStyle[] styles = config.getStyles();
        for (ComponentStyle element : styles) {
            write(element);
        }
        Serializer writer = new Serializer(jar);
        writer.setIndent(2);
        writer.write(doc);
        writer.flush();
        jar.closeEntry();
        for (Iterator iter = imagesToCopy.keySet().iterator(); iter.hasNext(); ) {
            String element = (String) iter.next();
            File pathToImage = (File) imagesToCopy.get(element);
            ZipEntry image = new ZipEntry(directory + "/" + element);
            jar.putNextEntry(image);
            FileInputStream input = new FileInputStream(pathToImage);
            int read = -1;
            while ((read = input.read()) != -1) {
                jar.write(read);
            }
            input.close();
            jar.flush();
            jar.closeEntry();
        }
        jar.flush();
        jar.close();
    }
